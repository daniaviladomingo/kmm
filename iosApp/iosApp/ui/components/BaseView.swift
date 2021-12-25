//
//  BaseView.swift
//  iosApp
//
//  Created by Daniel Ávila on 25/12/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
/*
struct BaseView<T, SuccessView : View>: View {
    private let resourceState: BasicUiState<T>
    private let successView: (T) -> SuccessView
    
    init(
        resourceState: BasicUiState<T>,
        successView:  @escaping (T) -> SuccessView,
        onTryAgain: () -> Void,
        onCheckAgain: () -> Void
    ) {
        self.resourceState = resourceState
        self.successView = successView
    }
    
    var body: some View {
        Text("asdf")
        
        
        switch self.resourceState {
            case is BasicUiStateEmpty:
                EmptyView()
            case is BasicUiStateLoading:
                LoadingView()
            case is BasicUiStateError:
                ErrorView()
            case let success as BasicUiStateSuccess<T>:
                //successView(success.data)
            default:
                break
        }
    }
}
*/

/*
struct BaseView_Previews: PreviewProvider {
    static var previews: some View {
        BaseView()
    }
}
 */
