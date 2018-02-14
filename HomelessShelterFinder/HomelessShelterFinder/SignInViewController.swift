//
//  SignInViewController.swift
//  HomelessShelterFinder
//
//  Created by Sanjay John on 2/12/18.
//  Copyright © 2018 Sanjay John. All rights reserved.
//

import UIKit

class SignInViewController: UIViewController {

    @IBOutlet weak var _username: UITextField!
    @IBOutlet weak var _password: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func SignInButton(_ sender: Any) {
        let username = _username.text
        let password = _password.text
        
        if (username?.isEmpty)! || (password?.isEmpty)!
        {
            return
        }
        if (username == "username@test.com") && (password == "password")
        {
            let SignedInPage = self.storyboard?.instantiateViewController(withIdentifier: "HomePageViewController") as! HomePageViewController
        
            self.present(SignedInPage, animated: true)
        }
    }
    
    @IBAction func CancelButton(_ sender: Any) {
        let WelcomePage = self.storyboard?.instantiateViewController(withIdentifier: "WelcomeViewController") as! WelcomeViewController
        
        self.present(WelcomePage, animated: true)
    }
    @IBAction func RegisterButton(_ sender: Any) {
        let RegisterPageAlso = self.storyboard?.instantiateViewController(withIdentifier: "RegisterPageViewController") as! RegisterPageViewController
        
        self.present(RegisterPageAlso, animated: true)
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
