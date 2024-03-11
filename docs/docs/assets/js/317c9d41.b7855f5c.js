"use strict";(self.webpackChunkdocs=self.webpackChunkdocs||[]).push([[328],{3905:function(e,t,n){n.d(t,{Zo:function(){return p},kt:function(){return f}});var r=n(7294);function i(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function s(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function a(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?s(Object(n),!0).forEach((function(t){i(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):s(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function o(e,t){if(null==e)return{};var n,r,i=function(e,t){if(null==e)return{};var n,r,i={},s=Object.keys(e);for(r=0;r<s.length;r++)n=s[r],t.indexOf(n)>=0||(i[n]=e[n]);return i}(e,t);if(Object.getOwnPropertySymbols){var s=Object.getOwnPropertySymbols(e);for(r=0;r<s.length;r++)n=s[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(i[n]=e[n])}return i}var c=r.createContext({}),l=function(e){var t=r.useContext(c),n=t;return e&&(n="function"==typeof e?e(t):a(a({},t),e)),n},p=function(e){var t=l(e.components);return r.createElement(c.Provider,{value:t},e.children)},u={inlineCode:"code",wrapper:function(e){var t=e.children;return r.createElement(r.Fragment,{},t)}},d=r.forwardRef((function(e,t){var n=e.components,i=e.mdxType,s=e.originalType,c=e.parentName,p=o(e,["components","mdxType","originalType","parentName"]),d=l(n),f=i,y=d["".concat(c,".").concat(f)]||d[f]||u[f]||s;return n?r.createElement(y,a(a({ref:t},p),{},{components:n})):r.createElement(y,a({ref:t},p))}));function f(e,t){var n=arguments,i=t&&t.mdxType;if("string"==typeof e||i){var s=n.length,a=new Array(s);a[0]=d;var o={};for(var c in t)hasOwnProperty.call(t,c)&&(o[c]=t[c]);o.originalType=e,o.mdxType="string"==typeof e?e:i,a[1]=o;for(var l=2;l<s;l++)a[l]=n[l];return r.createElement.apply(null,a)}return r.createElement.apply(null,n)}d.displayName="MDXCreateElement"},4058:function(e,t,n){n.r(t),n.d(t,{assets:function(){return p},contentTitle:function(){return c},default:function(){return f},frontMatter:function(){return o},metadata:function(){return l},toc:function(){return u}});var r=n(7462),i=n(3366),s=(n(7294),n(3905)),a=["components"],o={},c="synapse-client-test",l={unversionedId:"client/synapse-client-test",id:"client/synapse-client-test",title:"synapse-client-test",description:"Description",source:"@site/real_docs/client/synapse-client-test.md",sourceDirName:"client",slug:"/client/synapse-client-test",permalink:"/synapse/docs/client/synapse-client-test",draft:!1,tags:[],version:"current",frontMatter:{},sidebar:"tutorialSidebar",previous:{title:"synapse-client-soap",permalink:"/synapse/docs/client/synapse-client-soap"},next:{title:"synapse-data-couchbase",permalink:"/synapse/docs/data/synapse-data-couchbase"}},p={},u=[{value:"Description",id:"description",level:2},{value:"Usage",id:"usage",level:2}],d={toc:u};function f(e){var t=e.components,n=(0,i.Z)(e,a);return(0,s.kt)("wrapper",(0,r.Z)({},d,n,{components:t,mdxType:"MDXLayout"}),(0,s.kt)("h1",{id:"synapse-client-test"},"synapse-client-test"),(0,s.kt)("h2",{id:"description"},"Description"),(0,s.kt)("ul",null,(0,s.kt)("li",{parentName:"ul"},(0,s.kt)("p",{parentName:"li"},"This is the synapse module that provides the base classes to test the spring restful clients. It provides several\nout-of-the-box functionalities like:"),(0,s.kt)("ul",{parentName:"li"},(0,s.kt)("li",{parentName:"ul"},"An open to extension BaseRestClientUnitTest class which already calls several methods to unit test all the\nscenarios in the concrete client unit test class that extend this."),(0,s.kt)("li",{parentName:"ul"},"An open to extension BaseRestClientUnitIT class that will require only the concrete rest client IT class\nimplementing it to override one success method which will be the integration test method needed.")))),(0,s.kt)("h2",{id:"usage"},"Usage"),(0,s.kt)("ul",null,(0,s.kt)("li",{parentName:"ul"},"To utilize this module, add the following dependency to the pom.xml file:")),(0,s.kt)("pre",null,(0,s.kt)("code",{parentName:"pre"},"        <dependency>\n            <groupId>com.americanexpress</groupId>\n            <artifactId>synapse-client-test</artifactId>\n            <version>0.3.32-SNAPSHOT</version>\n        </dependency>\n")))}f.isMDXComponent=!0}}]);