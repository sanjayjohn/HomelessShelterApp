//
//  RegisterPageViewController.swift
//  HomelessShelterFinder
//
//  Created by Sanjay John on 2/13/18.
//  Copyright © 2018 Sanjay John. All rights reserved.
//

import UIKit

class RegisterPageViewController: UIViewController {

    @IBOutlet weak var _firstName: UITextField!
    @IBOutlet weak var _lastName: UITextField!
    @IBOutlet weak var _email: UITextField!
    @IBOutlet weak var _password: UITextField!
    @IBOutlet weak var _repeatPassword: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func CancelButton(_ sender: Any) {
        let SignedOutPage = self.storyboard?.instantiateViewController(withIdentifier: "SignInViewController") as! SignInViewController
        
        self.present(SignedOutPage, animated: true)
    }
    
    @IBAction func SignUpButton(_ sender: Any) {
//        let firstName = _firstName.text
//        let lastName = _lastName.text
//        let email = _email.text
//        let password = _password.text
//        let repeatPassword = _repeatPassword.text
        return
        
    }
    
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
