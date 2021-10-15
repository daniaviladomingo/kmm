//
//  CharactersFavoritesVM.swift
//  iosApp
//
//  Created by Daniel Ávila Domingo on 12/10/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import shared

class CharactersFavoritesVM : CharactersFavoritesViewModel, ObservableObject {
    @Published var listCharactersFavorites: [Character] = []
        
    override init() {
        super.init()

        collect(flow: uiState, collect: { data in
            let state = (((data as! CharactersFavoritesContractState).charactersFavorites) as BasicUiState<NSArray>)
            
            switch state {
            case let success as BasicUiStateSuccess<NSArray>:
                self.listCharactersFavorites = success.data as! [Character]
            default:
                break
            }
        })
    }
}
