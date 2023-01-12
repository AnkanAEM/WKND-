const inputField = document.getElementById("inputFile");
const form = document.getElementById('asset-upload')
console.log("This JS is working ")

// function readFile(e){

//     const fileReader = new FileReader()
//     fileReader.readAsArrayBuffer(inputField.files[0])

//     fileReader.addEventListener('load', ()=>{
//         const bufferArray = fileReader.result
//         console.log({bufferArray})
//         console.log(bufferArray[0]);
//     })
// }
// function uploadFile(){

// }

function handleInputChange(e){
    // readFile(e)
    let data = new FormData(form)
    let inputFile = data.get("inputFile")
    console.log({inputFile})
}

inputField.addEventListener('change', handleInputChange)