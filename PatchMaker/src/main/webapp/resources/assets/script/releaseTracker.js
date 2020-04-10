

$(document).ready(function() {
    $('#example').DataTable( {
        dom: 'fBrtilp',
        buttons: [
            'copyHtml5',
            'excelHtml5',
            'csvHtml5',
        ]
    });
} );