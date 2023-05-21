window.addEventListener('DOMContentLoaded', function () {
    'use strict';
    const btnFamilyRelationsCertificateIssues
        = document.querySelectorAll(".btn-certificate-of-family-relations-issue");

    btnFamilyRelationsCertificateIssues.forEach(function (btn) {
        btn.addEventListener('click', function () {
            const ans = confirm("가족관계증명서를 발급하시겠습니까?");
            if (ans) {
                const familyRelationsCertificateIssueForm
                    = btn.nextElementSibling;
                familyRelationsCertificateIssueForm.submit();
            }
        });
    });
});