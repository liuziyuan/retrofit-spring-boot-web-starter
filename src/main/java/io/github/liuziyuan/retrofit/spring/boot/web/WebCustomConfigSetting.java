package io.github.liuziyuan.retrofit.spring.boot.web;

import io.github.liuziyuan.retrofit.core.GlobalParamConfig;
import io.github.liuziyuan.retrofit.core.OverrideRule;
import io.github.liuziyuan.retrofit.core.builder.*;
import io.github.liuziyuan.retrofit.spring.boot.RetrofitComponent;

import java.util.ArrayList;

@RetrofitComponent
public class WebCustomConfigSetting implements GlobalParamConfig {

    @Override
    public boolean enable() {
        return true;
    }

    @Override
    public OverrideRule overwriteType() {
        return null;
    }

    @Override
    public String globalBaseUrl() {
        return null;
    }

    @Override
    public Class<? extends BaseCallAdapterFactoryBuilder>[] globalCallAdapterFactoryBuilderClazz() {
        return new ArrayList<Class<? extends BaseCallAdapterFactoryBuilder>>() {{
            add(BodyCallAdapterFactoryBuilder.class);
            add(GuavaCallAdapterFactoryBuilder.class);
        }}.toArray(new Class[0]);
    }

    @Override
    public Class<? extends BaseConverterFactoryBuilder>[] globalConverterFactoryBuilderClazz() {
        return new ArrayList<Class<? extends BaseConverterFactoryBuilder>>() {{
            add(JacksonConvertFactoryBuilder.class);
        }}.toArray(new Class[0]);
    }

    @Override
    public Class<? extends BaseOkHttpClientBuilder> globalOkHttpClientBuilderClazz() {
        return null;
    }

    @Override
    public Class<? extends BaseCallBackExecutorBuilder> globalCallBackExecutorBuilderClazz() {
        return null;
    }

    @Override
    public Class<? extends BaseCallFactoryBuilder> globalCallFactoryBuilderClazz() {
        return null;
    }

    @Override
    public String globalValidateEagerly() {
        return null;
    }
}
