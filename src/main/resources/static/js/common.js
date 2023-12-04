function parameter(name) {
    const url = new URLSearchParams(window.location.search);
    const value = url.get(name);
    return value ? value : null;
}

function addParameter(name, value) {
    const url = new URL(window.location.href);
    url.searchParams.set(name, value);
    window.history.replaceState(null, null, url.href);
}

function copyFieldFromUrl(name, formId) {
    let value = parameter(name);
    $(formId).find('[name="' + name + '"]').val(value);
    return value;
}

async function showErrorDialog(errors) {
    return new Promise((resolve, reject) => {
        const $dialog = $('<div>').addClass('error');
        const $list = $('<ul>').addClass('error-list');
        errors.forEach(error => {
            const $errorItem = $('<li>').text(error);
            $list.append($errorItem);
        });
        $dialog.append($list);

        const $ok = $('<button>').text('Ok');
        $ok.click(() => {
            $dialog.remove();
            resolve();
        });
        const $buttons = $('<div>').addClass('buttons')
            .append($ok);
        $dialog.append($buttons);

        $('body').append($dialog);
        $ok.focus();

        removeWhenEsc($dialog, () => resolve());
    });
}

function removeWhenEsc($dialog, onReject) {
    $(document).keydown((e) => {
        if (e.key === 'Escape') {
            $dialog.remove();
            $(document).off('keydown');
            if (!!onReject) {
                onReject();
            }
        }
    });
}

async function askDelete(message) {
    return new Promise((resolve, reject) => {
        const $dialog = $('<div>').addClass("ask");
        const $message = $('<div>').text(message).addClass('ask-message');
        $dialog.append($message);

        const $confirm = $('<button>').text('Yes');
        const $cancel = $('<button>').text('No');
        $confirm.click(() => {
            $dialog.remove();
            resolve();
        });
        $cancel.click(() => {
            $dialog.remove();
            reject();
        });
        const $buttons = $('<div>').addClass('buttons')
            .append($confirm, $cancel);

        $dialog.append($buttons);
        $('body').append($dialog);
        $confirm.focus();

        removeWhenEsc($dialog, () => reject());
    });
}

function copyToClipboard(text) {
    const tempTextArea = document.createElement('textarea');
    tempTextArea.value = text;
    document.body.appendChild(tempTextArea);
    tempTextArea.select();
    try {
        document.execCommand('copy');
        console.log('Text copied to clipboard');
    } catch (err) {
        console.error('Could not copy text', err);
    }
    document.body.removeChild(tempTextArea);
}

function linkClick(action, onClick) {
    return $('<span>')
        .addClass('link')
        .text(action)
        .click(onClick);
}