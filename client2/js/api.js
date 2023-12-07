var menuUrl="http://localhost:8080/menu"
const Menu= async function fetchMenu() {
    try {
        const response=await fetch("http://localhost:8080/menu");
        if(!response.ok){
            throw new Error('HTTP error! Status: ${response.status}');
        }
        const data=await response.json();
        return data;
    } catch (error) {
        console.log('Error fetching data'+error);
        throw error;
        
    }
    
}
export default Menu;