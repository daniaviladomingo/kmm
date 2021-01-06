//
//  BaseViewController.swift
//  iosApp
//
//  Created by Daniel Ávila Domingo on 01/01/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import UIKit
import shared

class BaseViewController<P : IBasePresenter>: UIViewController, IBaseView {
    var presenter: P?
        
    func managementResourceState(resource: Resource<AnyObject>) {
        
    }
    
    override func viewWillAppear(_ animated: Bool) {
        presenter?.attach(view: self)
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        presenter?.detach()
    }
    
    func showToast(message : String) {
        let alert = UIAlertController(title: nil, message: message, preferredStyle: .alert)
        alert.view.backgroundColor = UIColor.black
        alert.view.alpha = 0.6
        alert.view.layer.cornerRadius = 15
        
        self.present(alert, animated: true)
        
        DispatchQueue.main.asyncAfter(deadline: DispatchTime.now() + 2) {
            alert.dismiss(animated: true)
        }
    }
}
