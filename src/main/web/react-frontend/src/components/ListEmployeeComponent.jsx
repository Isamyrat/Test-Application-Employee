import React, { Component } from 'react';
import EmployeeService from '../services/EmployeeService';
import axios from 'axios';

class ListEmployeeComponent extends Component {
    constructor(props) {
        super(props);

        this.state = {
            employees: [],
            search: '',
            currentPage: 1,
            booksPerPage: 5,
        };
        this.addEmployee = this.addEmployee.bind(this);
        this.editEmployee = this.editEmployee.bind(this);
        this.deleteEmployee = this.deleteEmployee.bind(this);
        this.firstPage = this.firstPage.bind(this);
        this.prevPage = this.prevPage.bind(this);
        this.lastPage = this.lastPage.bind(this);
        this.nextPage = this.nextPage.bind(this);
    }

    viewEmployee(id) {
        this.props.history.push(`/view-employee/${id}`);
    }

    deleteEmployee(id) {
        EmployeeService.deleteEmployee(id).then((res) => {
            this.setState({
                employees: this.state.employees.filter(
                    (employee) => employee.id !== id
                ),
            });
        });
        //this.props.history.push(`/delete-employee/${id}`);
    }

    editEmployee(id) {
        this.props.history.push(`/update-employee/${id}`);
    }

    // componentDidMount() {
    //     EmployeeService.getEmployees().then((res) => {
    //         this.setState({ employees: res.data });
    //     });
    // }

    componentDidMount() {
        this.findAllBooks(this.state.currentPage);
    }

    findAllBooks(currentPage) {
        currentPage -= 1;
        axios
            .get(
                'http://localhost:8080/api/v1/employees?pageNumber=' +
                    currentPage +
                    '&pageSize=' +
                    this.state.booksPerPage
            )
            .then((response) => response.data)
            .then((data) => {
                this.setState({
                    employees: data.content,
                    totalPages: data.totalPages,
                    totalElements: data.totalElements,
                    currentPage: data.number + 1,
                });
            })
            .catch((error) => {
                console.log(error);
                this.props.history.push('/');
            });
    }
    firstPage = () => {
        let firstPage = 1;
        if (this.state.currentPage > firstPage) {
            this.findAllBooks(firstPage);
        }
    };

    prevPage = () => {
        let prevPage = 1;
        if (this.state.currentPage > prevPage) {
            this.findAllBooks(this.state.currentPage - prevPage);
        }
    };

    lastPage = () => {
        let condition = Math.ceil(
            this.state.totalElements / this.state.booksPerPage
        );
        if (this.state.currentPage < condition) {
            this.findAllBooks(condition);
        }
    };

    nextPage = () => {
        if (
            this.state.currentPage <
            Math.ceil(this.state.totalElements / this.state.booksPerPage)
        ) {
            this.findAllBooks(this.state.currentPage + 1);
        }
    };
    
  
    addEmployee() {
        this.props.history.push('/add-employee');
    }

    render() {
        const { currentPage, totalPages } = this.state;
        return (
            <div>
                <h2 className='text-center'> Employees List</h2>
                <div className='row'>
                    <button
                        className='btn btn-primary'
                        onClick={this.addEmployee}
                    >
                        Add Employee
                    </button>
                </div>

                <div className='row'>
                    <table className='table table-striped table-bordered'>
                        <thead>
                            <tr>
                                <th>Employee First Name</th>
                                <th>Employee Last Name</th>

                                <th>Actions</th>
                            </tr>
                        </thead>

                        <tbody>
                            {this.state.employees.map((employee) => (
                                <tr key={employee.id}>
                                    <td>{employee.firstName}</td>
                                    <td>{employee.lastName}</td>
                                    <td>
                                        <button
                                            onClick={() =>
                                                this.editEmployee(employee.id)
                                            }
                                            className='btn btn-info'
                                        >
                                            {' '}
                                            Update
                                        </button>
                                        <button
                                            style={{ marginLeft: '10px' }}
                                            onClick={() =>
                                                this.deleteEmployee(employee.id)
                                            }
                                            className='btn btn-danger'
                                        >
                                            {' '}
                                            Delete
                                        </button>
                                        <button
                                            style={{ marginLeft: '10px' }}
                                            onClick={() =>
                                                this.viewEmployee(employee.id)
                                            }
                                            className='btn btn-info'
                                        >
                                            {' '}
                                            View
                                        </button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>

                <div className='contaner'>
                    <div style={{ float: 'left' }}>
                        Showing Page {currentPage} of {totalPages}
                    </div>
                    <div style={{ float: 'right' }}>
                        <div size='sm'>
                            <div>
                                <button
                                    type='button'
                                    variant='outline-info'
                                    disabled={currentPage === 1 ? true : false}
                                    onClick={this.firstPage}
                                >
                                    First
                                </button>
                                <button
                                    type='button'
                                    variant='outline-info'
                                    disabled={currentPage === 1 ? true : false}
                                    onClick={this.prevPage}
                                >
                                    Prev
                                </button>
                           
                        
                            
                                <button
                                    type='button'
                                    variant='outline-info'
                                    disabled={
                                        currentPage === totalPages
                                            ? true
                                            : false
                                    }
                                    onClick={this.nextPage}
                                >
                                    Next
                                </button>
                                <button
                                    type='button'
                                    variant='outline-info'
                                    disabled={
                                        currentPage === totalPages
                                            ? true
                                            : false
                                    }
                                    onClick={this.lastPage}
                                >
                                    Last
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default ListEmployeeComponent;
