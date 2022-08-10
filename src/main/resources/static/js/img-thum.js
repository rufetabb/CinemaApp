$(document).ready(function(){
    $('#formInput').hide();
    $('#back').hide();
    var value=document.getElementsByClassName("update");
    var movieId=document.getElementsByClassName("movieId");
    $('#fileImage').change(function (){
        showImage(this);
    });
    $('#fileImageChange').change(function (){
        showImage(this);
    });
    $('#addMovie').click(function () {
        $('#addMovie').hide();
        $('#formInput').show();
        $('#allMovie').hide();
        $('#back').show();

        for(var i=0;i<value.length;i++){
            value[i].id="update"+i;
            movieId[i].id="movieId"+i;
            console.log(value[i].id);
        }

    });

    // $('.deleteBtn').click(function () {
    //     $('#edit').hide();
    //     $('#delete').hide();
    //     $('#formInput').hide();
    //     $('#allMovie').show();
    //     $('#back').show();
    //     $('#update').hide();
    //     $('.deleteBtn').show();
    // });
    $('#back').click(function () {
        $('#back').hide();
        $('#addMovie').show();
        $('#formInput').hide();
        $('#allMovie').show();
    });
    $('#backUpdate').click(function () {
        $('#back').show();
        $('#formInput').hide();
        $('#allMovie').show();
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