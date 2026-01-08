import axios from 'axios';
import React, { useEffect, useState } from 'react';

const PharmasistPage = () => {

    const [clicked, setclick] = useState(false)
    const [responsedata,setresponsedata]=useState([])
    const token=localStorage.getItem("token")

    const arr=[{"name":"shiv","class":"hjj","subject":"maths"},{"name":"shiv","class":"jgh","subject":"english"}];

    console.log("this is token ")
    console.log(token);

    useEffect(() => {
     console.log(responsedata);
    
      
    }, [responsedata])

    const hanndleSumit=async ()=>{
        setclick(true)
     try{

        const url='http://localhost:8080/pharmasists/stockscheck';
        
        const response=await axios.get(url,
            {
            headers:{
                Authorization:`Bearer ${token}`,
            },
        }
        );
        setresponsedata(response.data);
      

        

     }catch(error){
        console.log(error)
    }
    
    






    }
  return (
    <div>
        <button onClick={hanndleSumit}>updateStocks</button>
        {clicked && (
            <div>
            <table>
            {
          arr.map((item,index)=>(
            <tr key={index}>
            <td> {item.name}</td>
            <td> {item.class}</td>
            <td> {item.subject}</td>
            </tr>
          ))
        }
          </table>
         

         


            <table border="1" cellPadding="10" style={{ marginTop: "20px" }}>
  <thead>
    <tr>
      <th>Medicine Name</th>
      <th>Quantity</th>
      <th>Price</th>
    </tr>
  </thead>
  <tbody>
    {responsedata.map((item,index)=>(
        <tr key={index}>
          <td>{item.medicine_name}</td>
                <td>{item.quantity}</td>
                <td>₹{item.price}</td>
        </tr>

    ))}
  </tbody>
  </table>
  </div>
        )}


    </div>
  )
}

export default PharmasistPage