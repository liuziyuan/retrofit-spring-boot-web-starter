package io.github.liuziyuan.retrofit.spring.boot.web;

import com.google.common.util.concurrent.ListenableFuture;
import okhttp3.ResponseBody;
import retrofit2.*;

import javax.annotation.Nullable;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class BodyCallAdapterFactory extends CallAdapter.Factory {

    public static BodyCallAdapterFactory create() {
        return new BodyCallAdapterFactory();
    }

    @Nullable
    @Override
    public CallAdapter<?, ?> get(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (Call.class.isAssignableFrom(getRawType(type))) {
            return null;
        }
        if (CompletableFuture.class.isAssignableFrom(getRawType(type))) {
            return null;
        }
        if (ListenableFuture.class.isAssignableFrom(getRawType(type))) {
            return null;
        }
        if (Response.class.isAssignableFrom(getRawType(type))) {
            return null;
        }
        return new BodyCallAdapter<>(type, annotations, retrofit);
    }

    static final class BodyCallAdapter<R> implements CallAdapter<R, R> {

        private final Type returnType;

        private final Retrofit retrofit;

        private final Annotation[] annotations;

        BodyCallAdapter(Type returnType, Annotation[] annotations, Retrofit retrofit) {
            this.returnType = returnType;
            this.retrofit = retrofit;
            this.annotations = annotations;
        }

        @Override
        public Type responseType() {
            return returnType;
        }

        @Override
        public R adapt(Call<R> call) {
            Response<R> response;
            try {
                response = call.execute();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (response.isSuccessful()) {
                return response.body();
            }

            ResponseBody errorBody = response.errorBody();
            if (errorBody == null) {
                return null;
            }
            Converter<ResponseBody, R> converter = retrofit.responseBodyConverter(responseType(), annotations);
            try {
                return converter.convert(Objects.requireNonNull(errorBody));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
