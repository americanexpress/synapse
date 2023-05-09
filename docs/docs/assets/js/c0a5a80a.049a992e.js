"use strict";(self.webpackChunkdocs=self.webpackChunkdocs||[]).push([[434],{3905:function(e,t,n){n.d(t,{Zo:function(){return p},kt:function(){return f}});var a=n(7294);function r(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function o(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);t&&(a=a.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,a)}return n}function i(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?o(Object(n),!0).forEach((function(t){r(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):o(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function s(e,t){if(null==e)return{};var n,a,r=function(e,t){if(null==e)return{};var n,a,r={},o=Object.keys(e);for(a=0;a<o.length;a++)n=o[a],t.indexOf(n)>=0||(r[n]=e[n]);return r}(e,t);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);for(a=0;a<o.length;a++)n=o[a],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(r[n]=e[n])}return r}var c=a.createContext({}),u=function(e){var t=a.useContext(c),n=t;return e&&(n="function"==typeof e?e(t):i(i({},t),e)),n},p=function(e){var t=u(e.components);return a.createElement(c.Provider,{value:t},e.children)},l={inlineCode:"code",wrapper:function(e){var t=e.children;return a.createElement(a.Fragment,{},t)}},d=a.forwardRef((function(e,t){var n=e.components,r=e.mdxType,o=e.originalType,c=e.parentName,p=s(e,["components","mdxType","originalType","parentName"]),d=u(n),f=r,y=d["".concat(c,".").concat(f)]||d[f]||l[f]||o;return n?a.createElement(y,i(i({ref:t},p),{},{components:n})):a.createElement(y,i({ref:t},p))}));function f(e,t){var n=arguments,r=t&&t.mdxType;if("string"==typeof e||r){var o=n.length,i=new Array(o);i[0]=d;var s={};for(var c in t)hasOwnProperty.call(t,c)&&(s[c]=t[c]);s.originalType=e,s.mdxType="string"==typeof e?e:r,i[1]=s;for(var u=2;u<o;u++)i[u]=n[u];return a.createElement.apply(null,i)}return a.createElement.apply(null,n)}d.displayName="MDXCreateElement"},5895:function(e,t,n){n.r(t),n.d(t,{assets:function(){return p},contentTitle:function(){return c},default:function(){return f},frontMatter:function(){return s},metadata:function(){return u},toc:function(){return l}});var a=n(7462),r=n(3366),o=(n(7294),n(3905)),i=["components"],s={},c="synapse-data-couchbase",u={unversionedId:"data/synapse-data-couchbase",id:"data/synapse-data-couchbase",title:"synapse-data-couchbase",description:"Description",source:"@site/real_docs/data/synapse-data-couchbase.md",sourceDirName:"data",slug:"/data/synapse-data-couchbase",permalink:"/synapse/docs/data/synapse-data-couchbase",draft:!1,tags:[],version:"current",frontMatter:{},sidebar:"tutorialSidebar",previous:{title:"synapse-client-test",permalink:"/synapse/docs/client/synapse-client-test"},next:{title:"synapse-data-postgres",permalink:"/synapse/docs/data/synapse-data-postgres"}},p={},l=[{value:"Description",id:"description",level:2},{value:"Usage",id:"usage",level:2}],d={toc:l};function f(e){var t=e.components,n=(0,r.Z)(e,i);return(0,o.kt)("wrapper",(0,a.Z)({},d,n,{components:t,mdxType:"MDXLayout"}),(0,o.kt)("h1",{id:"synapse-data-couchbase"},"synapse-data-couchbase"),(0,o.kt)("h2",{id:"description"},"Description"),(0,o.kt)("ul",null,(0,o.kt)("li",{parentName:"ul"},(0,o.kt)("p",{parentName:"li"},"This is the synapse couchbase abstraction framework used whenever there is a need to connect to couchbase database and\nread from it. It provides several out-of-the-box functionalities like:"),(0,o.kt)("ul",{parentName:"li"},(0,o.kt)("li",{parentName:"ul"},"Built-in read functionalities to create multi-parameter and paginated dynamic queries based on Spring Data and\nQueryDSL.(There is not other library like this out-there, it still needs some work)."),(0,o.kt)("li",{parentName:"ul"},"An open to extension base configuration java file with the standard connection parameters needed to connect to\ncouchbase using Spring Data. The connection parameters will be provided by the property files on the module(s)that\nyou as a developer are creating that will use this base-data-postgres module as a dependency."),(0,o.kt)("li",{parentName:"ul"},"An open to extension BaseEntity containing the generated key identifier and the common auditing fields maintained\nby the Spring Data framework itself (createdBy, lastModifiedBy, createdDate, lastModifiedDate and version).")))),(0,o.kt)("h2",{id:"usage"},"Usage"),(0,o.kt)("ul",null,(0,o.kt)("li",{parentName:"ul"},"To utilize this module, add the following dependency to the pom.xml file:")),(0,o.kt)("pre",null,(0,o.kt)("code",{parentName:"pre"},"        <dependency>\n            <groupId>com.americanexpress</groupId>\n            <artifactId>synapse-data-couchbase</artifactId>\n            <version>0.3.16-SNAPSHOT</version>\n        </dependency>\n")))}f.isMDXComponent=!0}}]);