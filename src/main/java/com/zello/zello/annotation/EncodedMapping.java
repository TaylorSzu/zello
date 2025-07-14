package com.zello.zello.annotation;

import org.mapstruct.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Qualifier //serve para indicar ao MapStruct que ele deve usar esse metodo que tive essa anotação , para fazer a conversão personalizada quando ha mais valores iguais
@Target({ElementType.METHOD, ElementType.TYPE}) //isso é uma meta-anotação, que serve para dizer onde essa annotation pode ser aplicada
@Retention(RetentionPolicy.CLASS) // essa meta-anotação serve para dizer o ciclo de vida disponivel no codigo
public @interface EncodedMapping {
}
