//
//  CharactersViewController.swift
//  iosApp
//
//  Created by Daniel Ávila Domingo on 01/01/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import UIKit
import shared

class CharactersViewController: BaseViewController<CharactersPresenter>, ICharactersView {
    override func viewDidLoad() {
        presenter!.loadCharacters()
    }
    
    func displayCharacters(characters: [Character]) {
        characters.forEach{
            print($0)
        }
    }
}
