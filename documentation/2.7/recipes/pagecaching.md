---
---
# Web Page and Web Page Fragment Caching

 

## Introduction

This page provides an example of how to use the included filters to cache web pages and web page fragments.

## Problem

You'd like to improve the time it takes to return a page from your web application. Many of the pages in your application are not time or user specific and can be cached for a period of time.

## Solution

Cache the entirety of the web page, or a fragment of the web page for a period of time. Rather than having to generate the page on each page hit, it will be served out of the cache.

Modern application hardware should be able to server as many as 5,000 pages per second, affording a significant speedup in your application for pages that are frequently read but infrequently change.

## Discussion

There are no code changes required for this - your application server should support servlet filtering already. Simply update your web.xml file, re-deploy and you should see the speedup right away.

The basic steps you'll need to follow to configure Ehcache for web page caching are (note that these steps assume you already have Ehcache installed in your application):

1. Configure a servlet page filter in web.xml
1. Configure an appropriate cache in ehcache.xml
1. Start (or re-start) your application

The following settings should help you setup web caching for your application.

### Step 1 - Add a filter to your web.xml

The first thing you'll need to do is add a filter to enable page caching.

The following web.xml settings will enable a servlet filter for page caching:

~~~
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
     xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
     http://xmlns.jcp.org/xml/ns/javaee/web-app_2_5.xsd "
     version="2.5">

  <filter>
    <filter-name>SimplePageCachingFilter</filter-name>
    <filter-class>net.sf.ehcache.constructs.web.filter.SimplePageCachingFilter
    </filter-class>
  </filter>

  <!-- This is a filter chain. They are executed in the order below.
  Do not change the order. -->
  <filter-mapping>
    <filter-name>SimplePageCachingFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
</web-app>
~~~


### Step 2 - Configure an ehcache.xml

The second step to enabling web page caching is to configure ehcache with an appropriate ehcache.xml.

The following ehcache.xml file should configure a reasonable default ehcache:

~~~
<ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:noNamespaceSchemaLocation="../../main/config/ehcache.xsd">
    <cache name="SimplePageCachingFilter"
           maxEntriesLocalHeap="10000"
           maxEntriesLocalDisk="1000"
           eternal="false"
           overflowToDisk="true"
           timeToIdleSeconds="300"
           timeToLiveSeconds="600"
           memoryStoreEvictionPolicy="LFU"
            />
</ehcache>
~~~


### Step 3 - Start your application server

Now start your application server. Pages should be cached.

## More details

For more details and configuration options pertaining to web page and web page fragment caching, see the [Web Caching page](/documentation/2.7/modules/web-caching) in the user documentation.
