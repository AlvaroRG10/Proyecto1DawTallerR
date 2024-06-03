
var urlParams = new URLSearchParams(window.location.search);


function pintarFormularioCita(datos) {
	let html = `<form id="citaForm">
        <p class="title">Editar cita</p>
        <div>Introduzca los datos que desea editar:</div><br><br>

        <div class="inputContainer">
              <input type="text" id="nombre" name="nombre" class="input" required value="${datos.nombre}">
            <label for="nombre" class="label">Nombre</label>
        </div>

        <div class="inputContainer"> 
            <input type="text" id="email" name="email" class="input"  required value="${datos.email}">
              <label for="email" class="label">Email</label>
        </div>

        <div class="inputContainer">
             <input type="text" id="marca" name="marca" class="input"  required value="${datos.marca}">
               <label for="marca" class="label">Marca</label>
        </div>

        <div class="inputContainer">
            <input type="text" id="modelo" name="modelo" class="input" required value="${datos.modelo}">
            <label for="modelo" class="label">Modelo</label>
        </div>

        <div class="inputContainer">
            <input type="text"  id="matricula" name="matricula" class="input" required value="${datos.matricula}">
            <label for="matricula" class="label">Matrícula</label>
        </div>

       <div class="inputContainer">
             <input type="date" id="fecha" name="fecha" class="input" required value="${datos.fecha}">
             <label for="fecha" class="label">Fecha</label>
            </div>


        <input onclick="editarCita(${datos.idcita})" class="submitBtn" value="Enviar">
    </form>
`;

	document.getElementById("formularioCita").innerHTML = html;
}

function listaEditar() {
	const idCita = urlParams.get("id")


	const op = urlParams.get("op")

	console.log(idCita, op)

	fetch(`Listar?id=${idCita}&op=${op}`)
		.then(res => res.json())
		.then(res => pintarFormularioCita(res));



}


function editarCita(idCita) {
	const nombre = document.getElementById("nombre").value;
	const email = document.getElementById("email").value;
	const marca = document.getElementById("marca").value;
	const modelo = document.getElementById("modelo").value;
	const matricula = document.getElementById("matricula").value;
	const fecha = document.getElementById("fecha").value;

	const url = `GestionCitas?id=${idCita}&nombre=${encodeURIComponent(nombre)}&email=${encodeURIComponent(email)}&marca=${encodeURIComponent(marca)}&modelo=${encodeURIComponent(modelo)}&matricula=${encodeURIComponent(matricula)}&fecha=${encodeURIComponent(fecha)}`;

	fetch(url, {
		method: "PUT"
	})
		.then(response => {
			if (!response.ok) {
				throw new Error('Error en la solicitud');
			}
			// No es necesario procesar el cuerpo de la respuesta si no devuelve nada
			return response.text();  // Leer la respuesta como texto para confirmar el estado
		})
		.then(() => {
			console.log('Cita editada exitosamente');
			window.location.replace("listadocitas.html");
		})
		.catch(error => {
			console.error('Error al editar la cita:', error);
		});
}

















