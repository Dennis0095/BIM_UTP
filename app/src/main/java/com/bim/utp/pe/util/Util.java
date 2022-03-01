package com.bim.utp.pe.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.bim.utp.pe.BuildConfig;
import com.bim.utp.pe.local.model.BaseResponse;
import com.bim.utp.pe.mvvm.repository.services.Services;
import com.google.gson.Gson;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Util {

    private Context ctx;
    public static Retrofit retrofit;
    public static Services services;
    public static SharedPreferences sharedPref;

    public Util(Context ctx) {
        this.ctx = ctx;
    }
    public Util(){
    }

    public static void instanciarRetrofit(){
        if(retrofit == null && services ==null){
            retrofit = fnRetrofit();
            services = retrofit.create(Services.class);
        }

    }

    public static void instanciaShardPreference(SharedPreferences sharedPreferences){
        if(sharedPref == null){
            sharedPref = sharedPreferences;
        }
    }

    public static Retrofit fnRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.URI)
                .addConverterFactory(GsonConverterFactory.create())
                .client(Util.timeoutInternet())
                .build();
    }

    public static OkHttpClient timeoutInternet(){
        try{
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }
                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();


            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)

                    .sslSocketFactory(sslSocketFactory, (X509TrustManager)trustAllCerts[0])
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    })
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request original = chain.request();
                            Request.Builder requestBuilder = original.newBuilder()
                                    .addHeader("Content-Type","application/x-www-form-urlencoded")
                                    .method(original.method(), original.body());
                            //.addHeader("KEY_TOKEN_ACCESS", "9da791741bb73756bfce2fbd35e8556c06ccd557a8d4cb4d81")
                            //.addHeader("Accept","application/json")
                            Request request = requestBuilder.build();
                            return chain.proceed(request);
                        }
                    })
                    .build();
            return okHttpClient;

        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static BaseResponse parsearError(ResponseBody error, BaseResponse baseResponse, String codeError){

        try {
            error.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        baseResponse.setEstado(Constants.CODE_ERROR_PROCESAR);
        baseResponse.setMessage(Constants.ERROR_PROCESAR_CONSULTA);

        return baseResponse;
    }
}
