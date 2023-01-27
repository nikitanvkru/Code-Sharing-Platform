<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>Code</title>
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
<div class="container">
    <div><span id="load_date">${codeSnippet.date}</span></div>
    <div>The code will be available for <span id="time">${codeSnippet.time}</span> seconds</div>
    <div><span id="views">${codeSnippet.views}</span> more views allowed</div>
    <div>
            <pre id="code_snippet">
                <code>${codeSnippet.code}</code>
            </pre>
    </div>
</div>
</body>
</html>