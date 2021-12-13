//
//  CharactersViewModel.swift
//  iosApp
//
//  Created by Daniel Ávila Domingo on 6/10/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import shared

class CharactersVM: CharactersViewModel, ObservableObject {
    @Published var listCharacters: [Character] = []
    
    override init() {
        super.init()

        collect(flow: uiState, collect: { data in
            let uiState = data as! CharactersContractState
            let stateRequest = uiState.stateRequest
            
            switch stateRequest {
            case _ as StateRequest.Success:
                self.listCharacters = uiState.characters
            default:
                break
            }
        })
    }
}
