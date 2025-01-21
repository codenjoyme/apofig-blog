$(document).ready(function() {
    loadPages();

    async function loadPages() {
        let tag = parameter('tag');

        let pages = await getAllPages(tag);

        const $header = $('#header');
        let info = tag ? `: tag=[${tag}]` : '';
        $header.html($header.html() + info);

        const $pagesDiv = $('#pages');
        $pagesDiv.html('');

        pages.forEach(page => {
            const $pageDiv = $('<div>').addClass('page');
            const $info = $('<div>').addClass('info');

            $info.append(pageLink(page));

            let $buttons = $('<div>');
            $info.append($buttons);

            $pageDiv.append($info);
            $pagesDiv.append($pageDiv);
        });
    }
});