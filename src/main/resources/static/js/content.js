$(document).ready(function() {
    var fileName = parameter('fileName')

    loadContent();

    async function loadContent() {
        let content = await getPage(fileName);
        $('#content').html(content);
    }
});