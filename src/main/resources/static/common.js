function handleFormSubmit(formId, redirectUrl) {
    const form = document.getElementById(formId);

    if (!form) {
        console.error(`Error: Form with ID "${formId}" not found.`);
        return;
    }

    form.addEventListener('submit', function(event) {
        event.preventDefault(); // 阻止表單的預設提交行為

        const formData = new FormData(form);
        const data = {};

        // 將 FormData 轉換為 JSON 物件
        formData.forEach((value, key) => {
            // 注意：這裡假設表單 input 的 name 屬性與後端 DTO 的屬性名稱相同（camelCase）
            // 例如：前端 name="usDollars"，後端屬性為 private double usDollars;
            data[key] = value;
        });

        fetch(form.action, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        })
        .then(response => {
            if (!response.ok) {
                // 如果 HTTP 狀態碼不是 2xx，拋出錯誤
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(result => {
            console.log('Success:', result);
            // 提交成功後，導向到指定的 URL
            if (redirectUrl) {
                window.location.href = redirectUrl;
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert("提交失敗，請檢查輸入內容！"); // 可選：顯示錯誤訊息
        });
    });
}