// 주민 삭제
window.addEventListener('DOMContentLoaded', function () {
    'use strict';
    const btnDelete = document.querySelectorAll(".btn-delete");

    btnDelete.forEach(function (btn) {
        btn.addEventListener('click', function () {
            const ans = confirm("해당 주민을 삭제하시겠습니까?");
            if (ans) {
                const deleteForm = btn.nextElementSibling;
                deleteForm.submit();
            }
        });
    });
});