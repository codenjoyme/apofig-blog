/**
 * This is the model for the application. It contains all the functions that
 * interact with the server. Please note that the order of the functions and
 * its names are same as the order of the methods of PagesController.java
 * and PagesControllerTest.java.
 */

const DELETE = {method: 'DELETE'};
const PUT = {method: 'PUT'};
const POST = {method: 'POST'};

const GET_JSON = 'json';
const GET_TEXT = 'text';
const VOID = null;

async function getAllPages(tag) {
    var tagPart = !!tag ? `?tag=${tag}` : '';
    return fetchFor(`${contextPath}/api/pages${tagPart}`, GET_JSON);
}

async function getPage(fileName) {
    return fetchFor(`${contextPath}/api/pages/${fileName}`, GET_TEXT);
}

async function getAllTags() {
    return fetchFor(`${contextPath}/api/pages/tags`, GET_JSON);
}

/**
 * Utils methods
 */

function urlInfo(url, object, response) {
    let method = !!object ? object.method : 'GET';
    let status = response.status + ' ' + response.statusText;
    return `Something went wrong with request:\n[${method}]: ${url}\n${status}`;
}

async function fetchFor(url, method, object = null) {
    return new Promise(async (resolve, reject) => {
        let response = await fetch(url, object);

        if (response.status === 500) {
            const errorData = await response.json();
            let message = urlInfo(url, object, response);
            let errors = errorData.errors;
            errors.unshift(message);
            await showErrorDialog(errors);
            reject();
        } else if (!response.ok) {
            let message = urlInfo(url, object, response);
            await showErrorDialog([message]);
            reject();
        } else {
            if (method === VOID) {
                resolve();
            } else {
                resolve(await response[method]());
            }
        }
    });
}

/**
 * Link tags builders
 */

function actionLink(action, path, secret) {
    let secretParam = !!secret ? `&secret=${secret}` : '';
    return $('<a>')
        .attr('href', `${contextPath}/ui/${action}/${path}${secretParam}`)
        .text(action);
}

function pageLink(page) {
    return $('<a>')
        .attr('href', `${contextPath}/ui/page?fileName=${page.fileName}`)
        .text(`[${page.settings.time}] ${page.description}`);
}

function tagLink(tag) {
    return $('<a>')
        .attr('href', `${contextPath}/ui/pages?tag=${tag}`)
        .text(`[${tag}]`);
}

/**
 * Hrefs parts for other links
 */

function pagesListHref() {
    return `${contextPath}/ui/pages`;
}

function tagsListHref() {
    return `${contextPath}/ui/pages/tags`;
}

function swaggerHref() {
    return `${contextPath}/ui/swagger`;
}