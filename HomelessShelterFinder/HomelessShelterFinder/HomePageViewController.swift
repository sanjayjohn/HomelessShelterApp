//
//  HomePageViewController.swift
//  HomelessShelterFinder
//
//  Created by Sanjay John on 2/12/18.
//  Copyright © 2018 Sanjay John. All rights reserved.
//

import UIKit

class HomePageViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func SignOutButton(_ sender: Any) {
        let SignedOutPage = self.storyboard?.instantiateViewController(withIdentifier: "SignInViewController") as! SignInViewController
        
        self.present(SignedOutPage, animated: true)
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
