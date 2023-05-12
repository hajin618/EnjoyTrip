
let storagePoll = localStorage.getItem("poll");
let pollContent;
if (storagePoll) {
  var poll = JSON.parse(storagePoll);
  var sdate = poll.sdate;
  var edate = poll.edate;
  var question = poll.question;
  var answers = poll.answers;
  pollContent = `
        <div class="text-center text-secondary mb-3"><h5>[ 투표해주세요 ]</h5></div>
        <div class="fw-bold text-info bg-light"><h6>${question}</h6></div>
        <div class="text-start p-3">
            <ul class="nav flex-column">
        `;
  answers.forEach(function (answer) {
    pollContent += `
              <li class="nav-item">
                  <label><input type="radio" name="poll-answer" value="${answer}" /> ${answer}</label>
              </li>
            `;
  });
  pollContent += `
            </ul>
        </div>
        <div class="text-end">
            <button class="btn btn-outline-primary btn-sm" id="btn-poll-action">투표하기</button>
            <button class="btn btn-outline-danger btn-sm" id="btn-poll-result">결과보기</button>
        </div>
        <div class="text-end text-secondary mt-1">투표기간 : ${sdate} ~ ${edate}</div>
        `;
  document.querySelector("#poll-area").innerHTML = pollContent;
  // 설문조사 투표하기!!!
  document.querySelector("#btn-poll-action").addEventListener("click", function () {
    let selItem = document.querySelector("input[name=poll-answer]:checked");
    if (selItem) alert(selItem.value + "을 선택했습니다.");
    else alert("답변항목 하나 이상을 선택해 주세요.");
  });
} else {
  pollContent = `<h3>진행중인 투표가 없습니다.</h3>`;
  document.querySelector("#poll-area").innerHTML = pollContent;
}


// 로그인
//function login(){
//  var inputidValue = document.getElementById('inputid').value;
//console.log(inputidValue)
//var inputpwValue = document.getElementById('inputpw').value;
//console.log(inputpwValue)
//
//if (inputidValue == "ssafy" && inputpwValue == "1234") {
//  alert("로그인 성공");
//  document.querySelector("#header_nav_confirm_off").style.display = "none";
//  document.querySelector("#header_nav_confirm_on").style.display = "flex";
//
//  }
//else {
//  alert("다시 로그인 하세요");
//  }
//}
//
//// 로그아웃
//function logout() {
//  // id가 profile_img인 element의 src 속성의 값을 img/noimg.png로 설정.
//  document.querySelector("#header_nav_confirm_on").setAttribute("style", "display: none");
//  document.querySelector("#header_nav_confirm_off").setAttribute("style", "display: flex");
//}



//로그인
function login(){
	 var inputidValue = document.getElementById('inputid').value;
	 console.log(inputidValue)
	 var inputpwValue = document.getElementById('inputpw').value;
	 console.log(inputpwValue)

	 alert(inputidValue);
	 alert(inputpwValue);
	 if (inputidValue == "ssafy" && inputpwValue == "1234") {
		 alert("로그인 성공");
		 document.querySelector("#header_nav_confirm_off").style.display = "none";
		 document.querySelector("#header_nav_confirm_on").style.display = "flex";
		 document.querySelector("#header_nav_confirm_on_test").style.display = "flex";

	  	}
	else {
	  alert("다시 로그인 하세요");
	  }
	}

	// 로그아웃
function logout() {
	 // id가 profile_img인 element의 src 속성의 값을 img/noimg.png로 설정.
	 document.querySelector("header_nav_confirm_on_test").setAttribute("style", "display: none");
	 document.querySelector("#header_nav_confirm_on").setAttribute("style", "display: none");
	 document.querySelector("#header_nav_confirm_off").setAttribute("style", "display: flex");
}




