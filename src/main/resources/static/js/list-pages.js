$(document).ready(function() {
    loadPages();

    async function loadPages() {
        let secret = parameter('secret');
        let tag = parameter('tag');

        let pages = await getAllPages(tag);

        const $pagesDiv = $('#pages');
        $pagesDiv.html('');

        pages.forEach(page => {
            const $pageDiv = $('<div>').addClass('page');
            const $info = $('<div>').addClass('info');

            const $pageLink = pageLink(page);
            $info.append($pageLink);

            let $buttons = $('<div>');
            $info.append($buttons);

            $pageDiv.append($info);
            $pagesDiv.append($pageDiv);
        });
    }
});