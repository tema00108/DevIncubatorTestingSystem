const resultTableBody = document.getElementById('resultTableBody');
const dataContainer = document.getElementById('dataContainer');
let isReverseTest = false;


Array.from(resultTableBody.querySelectorAll('.test')).sort()

dataContainer.addEventListener('click', (event) => {
    const {target} = event;
    if (target.closest('#sortTestsButton')) {
        isReverseTest = !isReverseTest;
        reverseTests(target);
    }
});

function reverseTests(target) {
    target.closest('#sortTestsButton').classList.toggle('reverse');
    const reversedTests = Array.from(resultTableBody.querySelectorAll('.test')).reverse();
    resultTableBody.innerHTML = '';
    reversedTests.forEach(test => {
        resultTableBody.append(test);
    })
}

async function getUserTestStatistics(target) {
    const userId = target.value;
    const url = new URL(baseUrl + "/user/statistics");
    const params = {id: userId};
    url.search = new URLSearchParams(params).toString();
    const response = await fetch(url.toString());
    const result = await response.json();
    updateResult(result);
}
