//
//  CharactersFavoritesVM.swift
//  iosApp
//
//  Created by Daniel Ávila Domingo on 12/10/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import shared

class CharactersFavoritesVM : CharactersFavoritesViewModel, ObservableObject {
    @Published var state: CharactersFavoritesContractState =
    CharactersFavoritesContractState(charactersFavorites: BasicUiStateIdle.shared)
        
    override init() {
        super.init()
        
        collect(flow: uiState, collect: { state in
            self.state = state as! CharactersFavoritesContractState
        })
    }
}
