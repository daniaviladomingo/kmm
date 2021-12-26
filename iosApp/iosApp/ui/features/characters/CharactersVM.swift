//
//  CharactersViewModel.swift
//  iosApp
//
//  Created by Daniel Ávila Domingo on 6/10/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import shared

class CharactersVM: CharactersViewModel, ObservableObject {
    @Published var state: CharactersContractState = CharactersContractState(characters: BasicUiState<NSArray>())
    
    override init() {
        super.init()
        
        collect(flow: uiState, collect: { state in
            self.state = state as! CharactersContractState
        })
    }
}
