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
            let state = data as! CharactersFavoritesContractState
            
            let favorites = state.charactersFavorites
                                    
            switch favorites {
                case let success as BasicUiStateSuccess<NSArray>:
                    self.listCharactersFavorites = success.data as! [Character]
                case BasicUiStateEmpty.shared:
                    self.listCharactersFavorites =  []
                default:
                    break
                }
            }
        )
    }
}
