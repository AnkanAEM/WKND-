


const a =  document.getElementsByClassName("cmp-try__name");
    
    if (a.length) {
    
    
    
       a[0].addEventListener('click',  handleClick);
    
    
    
    }
    function handleClick(){
        console.log("Working");
        a[0].innerHTML="Clicked and JS working";
    }

