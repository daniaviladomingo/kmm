//
//  BaseViewController.swift
//  iosApp
//
//  Created by Daniel Ávila Domingo on 01/01/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import UIKit
import shared

class BaseViewController: UIViewController, IBaseView {
    func getPresenter() -> IBasePresenter  {
        preconditionFailure("This method must be overridden")
    }
    
    func managementResourceState(resource: Resource<AnyObject>) {
        
    }
    
    override func viewWillAppear(_ animated: Bool) {
        getPresenter().attach(view: self)
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        getPresenter().detach()
    }
}
