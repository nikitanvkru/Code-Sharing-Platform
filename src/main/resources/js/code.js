const bodyElement = document.querySelector("body");
const submitCodeButtonElement = document.querySelector("#send_snippet");
const codeSnippetElement = document.querySelector("#code_snippet");


fetch("http://localhost:8889/api/code", {
    method: "GET",
    headers: {
        "Accept": "application/json"
    }
})
    .then(response => response.json())
    .then(data =>  codeSnippetElement.textContent = data.code);


const createCodeSnippet = () => {

    const codeBody = {"code": codeSnippetElement.value};
    console.log("codeBody: ", JSON.stringify(codeBody));

    fetch("http://localhost:8889/api/code/new", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(codeBody)
    })
        .then(response => console.log("Code snippet has been created!"));
};

submitCodeButtonElement.addEventListener("click", createCodeSnippet);