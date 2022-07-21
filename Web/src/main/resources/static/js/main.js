btn1.addEventListener("click", postOneItem);
btnMany.addEventListener("click", postManyItems);

function print(data) {
    output.innerHTML = data.map(o => `<div><p>Id: ${o.id}</p><h1>${o.description}</h1></div>`).join('');
}

let c = 1;

function generateItem() {
    return { description: 'Item ' + c++ };
}

function printAll() {
    get("/items").then(print);
}

function postItem() {
    return post("/items", generateItem());
}

function postOneItem() {
    postItem().then(printAll);
}

function postManyItems() {
    Promise.all(Array.from({ length: 100 }, postItem)).then(printAll);
}

function post(url, data) {
    return fetch(url, {
        method: 'post',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        body: JSON.stringify(data)
    })
}

function get(url) {
    return fetch(url).then(res => res.json());
}

printAll();