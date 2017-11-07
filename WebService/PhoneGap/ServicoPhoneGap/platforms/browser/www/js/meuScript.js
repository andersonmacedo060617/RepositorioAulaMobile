/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    $("#btnClientes").click(function(){
        $.ajax({
        url: "http://192.168.1.81:8084/wsServicos/clienteWs",
        context: document.body
        }).done(function(data) {
            data.forEach(function(obj){
                $("#resultado").append(obj.nome + " Cidade: " + obj.cidade.nome + "/" + obj.cidade.estado + " <br/>");
            });

        });
    });  
    
    $("#btnNewCliente").click(function(){
        $.ajax({
            url: "http://192.168.1.81:8084/wsServicos/clienteSave", 
            type: 'POST',
            data: {nome: "...", idade:"15", valor:"123"},
            context:document.body
        });
    });
    
});



