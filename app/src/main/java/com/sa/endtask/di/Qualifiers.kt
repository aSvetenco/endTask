package com.sa.endtask.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import javax.inject.Qualifier
import javax.inject.Scope
import kotlin.annotation.AnnotationTarget.*
import kotlin.reflect.KClass

@Scope
@Retention
annotation class ActivityScoped

@Target(FUNCTION, PROPERTY_GETTER, PROPERTY_SETTER)
@Retention
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Qualifier
annotation class BaseUrl
