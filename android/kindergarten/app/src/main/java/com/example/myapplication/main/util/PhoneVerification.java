package com.example.myapplication.main.util;

/**

 * 详解:这是正则表达式Pattern.compile("***");这个是规范
 Pattern类用于创建一个正则表达式,生成一个Pattern对象并且编译一个正则表达式,也可以说创建一个匹配模式,
 它的构造方法是私有的,不可以直接创建,    但可以通过Pattern.complie(String regex)简单工厂方法创建一个正则表达式,
 轮到Matcher类登场了,Pattern.matcher(CharSequence input)返回一个Matcher对象.

 Matcher类的构造方法也是私有的,不能随意创建,只能通过Pattern.matcher(CharSequence input)方法得到该类的实例
 String.matches() 这个方法主要是返回是否匹配指定的字符串，如果匹配则为true,否则为false;
 **/
public class PhoneVerification {
    public static final String REGEX_MOBILE = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
}
