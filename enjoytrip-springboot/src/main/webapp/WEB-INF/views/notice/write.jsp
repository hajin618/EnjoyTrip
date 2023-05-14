<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <%@ include file="/include/head.jsp" %>
  </head>
  <body>
    <%@ include file="/include/header.jsp" %>
    <!-- 공지사항 컨테이너 -->
    <div class="container-fluid p-5">
        <!-- 제목 컨테이너 -->
        <div class="container-fluid">
            <div>
                <h2 style="text-align: center; padding-bottom: 10px; font-weight: bold;">여행 정보 등록</h2>
            </div>
            <div>
                <h2 style="text-align: center; color:blue; background-color:skyblue; border-radius: 10px; padding: 15px; font-weight: bold;">글작성</h2>
            </div>
            <div>
            <form id="form-register" method="POST" action="">
                <div class="form-group">
                    <label for="title" style="padding-top: 15px; font-weight: bold; font-size: 20px;">제목:</label>
                    <input type="text" class="form-control" id="subject"
                     placeholder="제목 입력..." name="notice_title"
                     maxlength="100" required="required"
                     pattern=".{4,100}"/>
                  </div>
                  <div class="form-group">
                 <label for="content" style="padding-top: 30px; font-weight: bold; font-size: 20px;">내용:</label>
                 <textarea class="form-control" rows="10" id="content"
                  name="notice_content" placeholder="내용 입력..."></textarea>
               </div>
              
               <button type="button" class="btn btn-primary" style="margin-top: 15px; font-weight: bold;" id="btn-register">글작성</button>
               <button type="button" class="btn btn-danger" style="margin-top: 15px; font-weight: bold;"  href="${root}/article?action=list" id="btn-list" >목록</button>
            </div>
		  </form>
        </div>
    </div>
    <!-- 하단 footer start -->
    <footer class="section-content p-4 mt-3 container-fluid">
        <%@ include file="/include/footer.jsp" %>
      </footer>
    <!-- 하단 footer end -->
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
      crossorigin="anonymous"
    ></script>
    <script src="/assets/js/main.js"></script>
    
    <script>
      document.querySelector("#btn-register").addEventListener("click", function () {
        if (!document.querySelector("#subject").value) {
          alert("제목 입력!!");
          return;
        } else if (!document.querySelector("#content").value) {
          alert("내용 입력!!");
          return;
        } else {
          let form = document.querySelector("#form-register");
          form.setAttribute("action", "${root}/notice/write");
          form.submit();
        }
      });
      
      document.querySelector("#btn-list").addEventListener("click", function () {
          location.href = "${root}/article?action=list&pgno=1&key=&word=";
        });
    </script>    
  </body>
</html>

