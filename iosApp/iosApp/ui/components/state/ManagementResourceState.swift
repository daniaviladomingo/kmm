//
//  BaseObservable.swift
//  iosApp
//
//  Created by Daniel Ávila on 17/12/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import shared
import SwiftUI

protocol ManagementResourceState : ObservableObject {
    func managementResourceState<T>(resourceState: BasicUiState<T>, successClosure: (T?) -> Void)
}

extension ManagementResourceState {
    func managementResourceState<T>(resourceState: BasicUiState<T>, successClosure: (T?) -> Void) {
        switch resourceState {
            case is BasicUiStateEmpty:
                EmptyView()
            case is BasicUiStateLoading:
                LoadingView()
            case is BasicUiStateError:
                ErrorView()
            case let success as BasicUiStateSuccess<T>:
                successClosure(success.data)
            default:
                break
        }
    }
}
