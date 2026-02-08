import axios from 'axios';
import React, { useEffect, useState } from 'react';

const PharmasistPage = () => {

  const [clicked, setclick] = useState(false)
  const [responsedata, setresponsedata] = useState([])
  const [add, setadd] = useState("")
  const token = localStorage.getItem("token")
  const [medicinename, setmedicinename] = useState('')
  const [quantity, setquantity] = useState('')
  const [price, setprice] = useState('')
  const [editindex, setindex] = useState(null)
  const [deleteindex, setdel] = useState(null)

  useEffect(() => {
    console.log("Updated values:", {
      medicinename,
      quantity,
      price
    });
  }, [medicinename, quantity, price]);

  const handledelete = async (item, index) => {
    console.log("entered");
    const confirm = window.confirm("Are you sure brooo");

    const url = 'http://localhost:8080/pharmasists/stock/delete'

    if (confirm) {
      console.log("confirmed")
      const response = await axios.delete(url, {
        data: {
          medicine_name: item.medicine_name,
          quantity: item.quantity,
          price: item.price
        },
        headers: {
          Authorization: `Bearer ${token}`
        }
      });
      console.log(response);
    }
  }

  useEffect(() => {
    console.log(responsedata);
  }, [responsedata])

  const hanndleSumit = async () => {
    setclick(true)
    try {
      const url = 'http://localhost:8080/pharmasists/stockscheck';

      const response = await axios.get(url,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      setresponsedata(response.data);

    } catch (error) {
      console.log(error)
    }
  }

  const handleadd = () => {
    setadd("ADD")
    console.log(medicinename);
    console.log(quantity);
    console.log(price);
  }

  const handleaddpostrequest = async () => {
    const url = 'http://localhost:8080/pharmasists/stocks/add'
    const data = {
      "medicine_name": medicinename,
      "quantity": quantity,
      "price": price
    }

    const response = await axios.post(url, data, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    })

    console.log(response);
  }

  const handleedit = (item, index) => {
    setindex(index)

    setmedicinename(item.medicine_name)
    setquantity(item.quantity)
    setprice(item.price)
  }

  const handleupdatesubmit = async () => {
    console.log("inside the function ");
    const url = 'http://localhost:8080/pharmasists/stock/update'
    const data = {
      "medicine_name": medicinename,
      "quantity": quantity,
      "price": price
    }

    const response = await axios.patch(url, data, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    })
  }

  return (
    <div className="pharmacist-container">

      <button className="pharmacist-btn pharmacist-btn-primary" onClick={hanndleSumit}>
        updateStocks
      </button>

      {clicked && (
        <div className="pharmacist-content">

          <button className="pharmacist-btn pharmacist-btn-secondary" onClick={handleadd}>
            add
          </button>

          <table className="pharmacist-table" border="1" cellPadding="10" style={{ marginTop: "20px" }}>
            <thead className="pharmacist-table-head">
              <tr className="pharmacist-table-row">
                <th className="pharmacist-table-th">Medicine Name</th>
                <th className="pharmacist-table-th">Quantity</th>
                <th className="pharmacist-table-th">Price</th>
                <th className="pharmacist-table-th">Edit</th>
                <th className="pharmacist-table-th">Delete</th>
              </tr>
            </thead>

            <tbody className="pharmacist-table-body">

              {add == "ADD" && (
                <tr className="pharmacist-table-row">
                  <td className="pharmacist-table-td">
                    <input className="pharmacist-input" type='text' onChange={(e) => { setmedicinename(e.target.value); console.log(medicinename) }} />
                  </td>

                  <td className="pharmacist-table-td">
                    <input className="pharmacist-input" type='text' onChange={(e) => { setquantity(e.target.value); console.log(quantity) }} />
                  </td>

                  <td className="pharmacist-table-td">
                    <input className="pharmacist-input" type='text' onChange={(e) => { setprice(e.target.value); console.log(price) }} />
                  </td>

                  <td className="pharmacist-table-td">
                    <button className="pharmacist-btn pharmacist-btn-primary" onClick={handleaddpostrequest}>
                      save
                    </button>
                  </td>
                </tr>
              )}

              {responsedata.map((item, index) => (
                <tr className="pharmacist-table-row" key={index}>
                  {editindex == index ? (
                    <>
                      <td className="pharmacist-table-td">
                        <input className="pharmacist-input" type='text' value={medicinename} disabled />
                      </td>

                      <td className="pharmacist-table-td">
                        <input className="pharmacist-input" type='text' value={quantity} onChange={(e) => setquantity(e.target.value)} />
                      </td>

                      <td className="pharmacist-table-td">
                        <input className="pharmacist-input" type='text' value={price} onChange={(e) => setprice(e.target.value)} />
                      </td>

                      <button className="pharmacist-btn pharmacist-btn-primary" onClick={handleupdatesubmit}>
                        save
                      </button>

                      <button className="pharmacist-btn pharmacist-btn-secondary">
                        cancel
                      </button>
                    </>
                  ) : (
                    <>
                      <td className="pharmacist-table-td">{item.medicine_name}</td>
                      <td className="pharmacist-table-td">{item.quantity}</td>
                      <td className="pharmacist-table-td">₹{item.price}</td>

                      <td className="pharmacist-table-td">
                        <button className="pharmacist-btn pharmacist-btn-secondary" onClick={() => handleedit(item, index)}>
                          EDIT
                        </button>
                      </td>

                      <td className="pharmacist-table-td">
                        <button className="pharmacist-btn pharmacist-btn-danger" onClick={() => handledelete(item, index)}>
                          DELETE
                        </button>
                      </td>
                    </>
                  )}
                </tr>
              ))}

            </tbody>
          </table>

        </div>
      )}

    </div>
  )
}

export default PharmasistPage;
