window.addEventListener('DOMContentLoaded', function () {
    'use strict';

    // 가족관계증명서 발급 버튼
    const btnIssueCertificateOfFamilyRelations
        = document.querySelectorAll(".btn-issue-certificate-of-family-relations");

    btnIssueCertificateOfFamilyRelations.forEach(function (btn) {
        btn.addEventListener('click', function () {
            const ans = confirm("가족관계증명서를 발급하시겠습니까?");
            if (ans) {
                const IssueCertificateOfFamilyRelationsForm
                    = btn.nextElementSibling;
                IssueCertificateOfFamilyRelationsForm.submit();
            }
        });
    });

    // 주민등록등본 발급 버튼
    const btnIssueResidentRegister
        = document.querySelectorAll(".btn-issue-resident-register");

    btnIssueResidentRegister.forEach(function (btn) {
        btn.addEventListener('click', function () {
            const ans = confirm("주민등록등본을 발급하시겠습니까?");
            if (ans) {
                const IssueResidentRegisterForm
                    = btn.nextElementSibling;
                IssueResidentRegisterForm.submit();
            }
        });
    });

    // 출생신고서 발급 버튼
    const btnIssueBirthCertificate
        = document.querySelectorAll(".btn-issue-birth-certificate");

    btnIssueBirthCertificate.forEach(function (btn) {
        btn.addEventListener('click', function () {
            const ans = confirm("출생신고서를 발급하시겠습니까?");
            if (ans) {
                const IssueResidentRegisterForm
                    = btn.nextElementSibling;
                IssueResidentRegisterForm.submit();
            }
        });
    });
});