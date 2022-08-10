$(document).ready(function(){
    $('#formInput').show();

    var value=document.getElementsByClassName("update");
    var movieId=document.getElementsByClassName("movieId");
    $('#fileImage').change(function (){
        showImage(this);
    });
    $('#fileImageChange').change(function (){
        showImage(this);
    });


    function showImage(fileInput){
        file= fileInput.files[0];
        reader=new FileReader();
        reader.onload=function (data){

            $('#changeImage').attr('src',data.target.result);
        }

        reader.readAsDataURL(file);

    }


})