# Web service for posting and viewing code snippets
GET /api/code/N should return JSON with the N-th uploaded code snippet.
GET /code/N should return HTML that contains the N-th uploaded code snippet.
POST /api/code/new should take a JSON object with a single field code, use it as the current code snippet, and return JSON with a single field id. ID is the unique number of the code snippet that can help you access it via the endpoint GET /code/N.
GET /code/new should be the same as in the second and third stages.
GET /api/code/latest should return a JSON array with 10 most recently uploaded code snippets sorted from the newest to the oldest.
GET /code/latest should return HTML that contains 10 most recently uploaded code snippets. Use the title Latest for this page.