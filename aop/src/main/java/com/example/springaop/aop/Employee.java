package com.example.springaop.aop;

	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.Table;

	import org.springframework.context.annotation.Configuration;

	@Configuration
	@Entity
	@Table(name = "employees")
	public class Employee {
		//@Value("${emp.id}")
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long id;
		//@ApiModelProperty(notes = "employee's first name")
		private String firstName;
		//@ApiModelProperty(notes = "employee's last name")
		private String lastName;
		//@ApiModelProperty(notes = "employee's email id")
		private String emailId;

		public Employee() {

		}

		public Employee(String firstName, String lastName, String emailId) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.emailId = emailId;
		}

		
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}

		@Column(name = "first_name", nullable = false)
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		@Column(name = "last_name", nullable = false)
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		@Column(name = "email_address", nullable = false)
		public String getEmailId() {
			return emailId;
		}
		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}

		@Override
		public String toString() {
			return "\nEmployee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
					+ "]";
		}
		
		
	}





