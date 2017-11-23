package cn.compal.wolf.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by wolf on 2017/9/2.
 */

@Scope
@Retention(RUNTIME)
public @interface FragmentScope
{
}
