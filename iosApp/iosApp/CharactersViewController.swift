//
//  CharactersViewController.swift
//  iosApp
//
//  Created by Daniel Ávila Domingo on 01/01/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import UIKit
import shared

class CharactersViewController: BaseViewController, ICharactersView {
    private let presenter: CharactersPresenter = CharactersPresenter(getCharacterUseCase: <#T##GetCharacterUseCase#>, executor: <#T##Executor#>)
    
    override func getPresenter() -> IBasePresenter {
        return CharactersPresenter
    }
    
    func displayCharacters(characters: [Character]) {
        <#code#>
    }
}
