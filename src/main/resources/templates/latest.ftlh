<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>Latest</title>
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
<div class="container">
    <#list recentCodes as codeSnippet>
        <div>
            <span id="load_date">${codeSnippet.date}</span>
            <div>
                <pre id="code_snippet">
                    <code>${codeSnippet.code}</code>
                </pre>
            </div>
        </div>
    </#list>
</div>
</body>
</html>