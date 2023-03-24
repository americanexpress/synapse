"use strict";(self.webpackChunkdocs=self.webpackChunkdocs||[]).push([[69],{3905:function(e,t,r){r.d(t,{Zo:function(){return p},kt:function(){return f}});var n=r(7294);function s(e,t,r){return t in e?Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}):e[t]=r,e}function o(e,t){var r=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),r.push.apply(r,n)}return r}function i(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?o(Object(r),!0).forEach((function(t){s(e,t,r[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):o(Object(r)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))}))}return e}function a(e,t){if(null==e)return{};var r,n,s=function(e,t){if(null==e)return{};var r,n,s={},o=Object.keys(e);for(n=0;n<o.length;n++)r=o[n],t.indexOf(r)>=0||(s[r]=e[r]);return s}(e,t);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);for(n=0;n<o.length;n++)r=o[n],t.indexOf(r)>=0||Object.prototype.propertyIsEnumerable.call(e,r)&&(s[r]=e[r])}return s}var c=n.createContext({}),l=function(e){var t=n.useContext(c),r=t;return e&&(r="function"==typeof e?e(t):i(i({},t),e)),r},p=function(e){var t=l(e.components);return n.createElement(c.Provider,{value:t},e.children)},u={inlineCode:"code",wrapper:function(e){var t=e.children;return n.createElement(n.Fragment,{},t)}},d=n.forwardRef((function(e,t){var r=e.components,s=e.mdxType,o=e.originalType,c=e.parentName,p=a(e,["components","mdxType","originalType","parentName"]),d=l(r),f=s,y=d["".concat(c,".").concat(f)]||d[f]||u[f]||o;return r?n.createElement(y,i(i({ref:t},p),{},{components:r})):n.createElement(y,i({ref:t},p))}));function f(e,t){var r=arguments,s=t&&t.mdxType;if("string"==typeof e||s){var o=r.length,i=new Array(o);i[0]=d;var a={};for(var c in t)hasOwnProperty.call(t,c)&&(a[c]=t[c]);a.originalType=e,a.mdxType="string"==typeof e?e:s,i[1]=a;for(var l=2;l<o;l++)i[l]=r[l];return n.createElement.apply(null,i)}return n.createElement.apply(null,r)}d.displayName="MDXCreateElement"},8289:function(e,t,r){r.r(t),r.d(t,{assets:function(){return p},contentTitle:function(){return c},default:function(){return f},frontMatter:function(){return a},metadata:function(){return l},toc:function(){return u}});var n=r(7462),s=r(3366),o=(r(7294),r(3905)),i=["components"],a={},c="synapse-service-test",l={unversionedId:"service/synapse-service-test",id:"service/synapse-service-test",title:"synapse-service-test",description:"Description",source:"@site/real_docs/service/synapse-service-test.md",sourceDirName:"service",slug:"/service/synapse-service-test",permalink:"/synapse/docs/service/synapse-service-test",draft:!1,tags:[],version:"current",frontMatter:{},sidebar:"tutorialSidebar",previous:{title:"synapse-service-rest",permalink:"/synapse/docs/service/synapse-service-rest"},next:{title:"synapse-utilities-common",permalink:"/synapse/docs/utility/synapse-utilities-common"}},p={},u=[{value:"Description",id:"description",level:2},{value:"Usage",id:"usage",level:2}],d={toc:u};function f(e){var t=e.components,r=(0,s.Z)(e,i);return(0,o.kt)("wrapper",(0,n.Z)({},d,r,{components:t,mdxType:"MDXLayout"}),(0,o.kt)("h1",{id:"synapse-service-test"},"synapse-service-test"),(0,o.kt)("h2",{id:"description"},"Description"),(0,o.kt)("ul",null,(0,o.kt)("li",{parentName:"ul"},(0,o.kt)("p",{parentName:"li"},"This is the synapse module that provides the base classes to test the controllers. It provides several out-of-the-box\nfunctionalities like:"),(0,o.kt)("ul",{parentName:"li"},(0,o.kt)("li",{parentName:"ul"},"An open to extension BaseControllerTest class with several overloaded methods create the slice test methods of any\npossible restful call made to your controllers."),(0,o.kt)("li",{parentName:"ul"},"An open to extension BaseControllerIT class with several overloaded methods to create the integration test methods\nof any possible restful call made to your controllers.")))),(0,o.kt)("h2",{id:"usage"},"Usage"),(0,o.kt)("ul",null,(0,o.kt)("li",{parentName:"ul"},"To utilize this module, add the following dependency to the pom.xml file:")),(0,o.kt)("pre",null,(0,o.kt)("code",{parentName:"pre"},"        <dependency>\n            <groupId>com.americanexpress</groupId>\n            <artifactId>synapse-service-test</artifactId>\n            <version>0.3.13-SNAPSHOT</version>\n        </dependency>\n")))}f.isMDXComponent=!0}}]);