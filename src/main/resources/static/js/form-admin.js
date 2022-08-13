$(document).ready(function(){
    $('#addAdmin').show();
    $('#back').hide();
    $('#addAdmin').click(function () {
        $('#back').show();
        $('#table-admin').hide();
        $('#formInput').show();
        $('#addAdmin').hide();

    })
    $('#back').click(function () {
        $('#back').hide();
        $('#table-admin').show();
        $('#formInput').hide();
        $('#addAdmin').show();

    })


 if($('#error').val()=="error"){
     $('#back').show();
     $('#table-admin').hide();
     $('#formInput').show();
     $('#addAdmin').hide();
 }
 //    $http.get('/admin/admin-user?error', function(data){
 //        $('#back').show();
 //        $('#table-admin').hide();
 //        $('#formInput').show();
 //        $('#addAdmin').hide();
 //
 //    })

})