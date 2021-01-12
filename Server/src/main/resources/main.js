
if(location.pathname.includes("task")) {
    function getResult() {
        let ans = document.getElementById("answer");
        axios({
            method: 'post',
            url: '/api/task',
            data: document.getElementById("inp").value,
        }).then((res) => {
            ans.innerText = "Ответ: " + res.data.toString();
        });
    }
} else if(location.pathname.includes("students")) {
    let saveFlag = true;
    let form = document.getElementsByClassName("form-wrap")[0];
    let addBtn = document.getElementById("addbtn");
    let saveBtn = document.getElementsByClassName("saveBtn")[0];
    let arr = document.getElementsByName("field");
    let table = document.getElementsByClassName("stud-table")[0].getElementsByTagName("tbody")[0];
    let target;
    let buttonsArr = document.getElementsByTagName("button");
    addBtn.onclick = () => {
        initForm();
        saveFlag = true;
    };

    function initForm() {
        form.style.display = "block";
        arr.forEach((s) => {
            s.value = "";
        });
        disableButtons();
    }

    function disableButtons() {
        for (i = 0; i < buttonsArr.length; i++) {
            if (buttonsArr[i] === saveBtn)
                continue;
            buttonsArr[i].disabled = true;
        }
    }

    function enableButtons() {
        for (i = 0; i < buttonsArr.length; i++) {
            buttonsArr[i].disabled = false;
        }
    }

    saveBtn.onclick = () => {
        if (saveFlag) {
            let row = document.createElement("tr");
            table.appendChild(row);
            let td1 = document.createElement("td");
            let td2 = document.createElement("td");
            let td3 = document.createElement("td");
            let td4 = document.createElement("td");
            row.appendChild(td1);
            row.appendChild(td2);
            row.appendChild(td3);
            row.appendChild(td4);
            const student = {
                FIO: arr[0].value,
                age: parseInt(arr[1].value)
            };
            axios({
                method: "post",
                url: "/api/students",
                data: JSON.stringify(student)
            }).then((s) => {
                td1.innerHTML = s.data;
            });
            td4.innerHTML = '<button class="delBut tbt">Удалить</button><button class="editBut tbt">Редактировать</button>';
            td2.innerHTML = arr[0].value;
            td3.innerHTML = arr[1].value;
            td4.children[0].onclick = () => {
                delRow(td4.children[0]);
            };
            td4.children[1].onclick = () => {
                editRow(td4.children[0]);
            }
            form.style.display = "none";
        } else {
            target.children[1].innerHTML = arr[0].value;
            target.children[2].innerHTML = arr[1].value;
            form.style.display = "none";
            const student = {
                FIO: arr[0].value,
                age: arr[1].value
            };
            axios({
                method: "put",
                url: `/api/students/${target.children[0].innerHTML == null ? 0 : target.children[0].innerHTML}`,
                data: JSON.stringify(student)
            }).then((res) => console.log("done"));
        }
        enableButtons();
    };

    function delRow(row) {
        target = row.parentNode.parentNode.children[0].innerHTML;
        console.log(row.parentNode.parentNode.children[0].innerHTML);
        axios({
            method: "delete",
            url: `/api/students/${target}`,
        }).then((s) => s);
        table.deleteRow(row.parentNode.parentNode.rowIndex);
    }

    function editRow(row) {
        form.style.display = "block";
        disableButtons();
        saveFlag = false;
        target = row.parentNode.parentNode; // row
        arr[0].value = target.children[1].innerHTML;
        arr[1].value = target.children[2].innerHTML;
    }
}